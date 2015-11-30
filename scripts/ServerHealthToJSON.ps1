
$file = get-Content H:\IRAD\computers.txt  # Replace it with your TXT file which contain Name of Computers 

foreach ( $args in $file) {
	
	#get the computer name
	$systemname = get-WmiObject win32_computersystem | select-object -expandproperty name
	
	#get server uptime
	$wmiobject = get-wmiobject -class win32_operatingsystem
	$computerUpTimeDays = ( ($wmiobject.converttodatetime($wmiobject.localdatetime))`
		- ($wmiobject.converttodatetime($wmiobject.lastbootuptime)) ).TotalDays
	
	$formattedComputerUpTimeDays = "{0:N2}" -f ($computerUpTimeDays)
	
	write-host "computer uptime = $computerUpTimeDays"
	
	#get disk usage for drive types [fixed local disk, network disk].  drivetype 3 is local disk, drivetype 4 is network disk
	$volumes = get-WmiObject win32_logicaldisk -ComputerName $args -Filter `
	"DriveType=3 or DriveType=4"`
	| Select-Object DeviceID,`
	VolumeName,`
	@{Label="TotalSize";Expression={$_.Size / 1kb -as [long] }},`
	@{Label="FreeSize";Expression={$_.freespace / 1kb -as [long] }},`
	@{Label="PercentFree";Expression={"{0:N2}" -f ($_.freespace/$_.Size * 100) } }

	#get total cpu usage
	#collect usage sample every 2 seconds 10 times
	$averageTotalCPUUsage = get-counter -Counter "\Processor(_Total)\% Processor Time" `
	-SampleInterval 2 -MaxSamples 10 `
	| select-object -expandproperty countersamples `
	| select-object -expandproperty cookedvalue `
	| measure-object -average `
	| select-object average
	
	$formattedAveTotalCPUUsage = "{0:N2}" -f ($averageTotalCPUUsage.Average)
	
	$numDevices = $volumes.Count
	write-host "Number of volumes = $numDevices"
		
	#get percent physical memory free
	$percentPhysicalMemoryFree = $wmiobject `
	| Select-Object @{Name="PercentMemFree";Expression={"{0:N2}" -f ((($_.FreePhysicalMemory/$_.TotalVisibleMemorySize)*100))}}
	
	#format JSON string	
	$jsonOutput = "{`n`t ""serverName""`: ""$($systemname)"","
	$jsonOutput += "`n`t ""disks""`: ["
	

	$i = 0
	foreach($vol in $Volumes) {
		if($i -eq ($numDevices - 1)){
			$jsonOutput += "`n`t`t {""deviceID""`: ""$($vol.DeviceID)"","
			$jsonOutput += " ""totalSizeKB""`: $($vol.TotalSize),"
			$jsonOutput += " ""freeSizeKB""`: $($vol.FreeSize),"
			$jsonOutput += " ""percentDiskFree""`: $($vol.PercentFree)}"
		} else {
			$jsonOutput += "`n`t`t {""deviceID""`: ""$($vol.DeviceID)"","
			$jsonOutput += " ""totalSizeKB""`: $($vol.TotalSize),"
			$jsonOutput += " ""freeSizeKB""`: $($vol.FreeSize),"
			$jsonOutput += " ""percentDiskFree""`: $($vol.PercentFree)},"
		}
		$i++
	}
	#add array closing bracket
	$jsonOutput += "`n`t ],"
	
	#writeout server uptime
	$jsonOutput += "`n`t ""serverUpTimeDays""`: $($formattedComputerUpTimeDays)," 
	#writeout cpu usage
	$jsonOutput += "`n`t ""percentCPUUsage""`: $($formattedAveTotalCPUUsage),"
	#writeout percent physical memory free
	$jsonOutput += "`n`t ""percentMemFree""`: $($percentPhysicalMemoryFree.PercentMemFree)"
	
	#close json output with final curly bracket
	$jsonOutput += "`n}"
	
}

#change the path as appropriate
$tempfile = "H:\IRAD\serverhealth.json.tmp"
$outputfile = "H:\IRAD\serverhealth.json"

write-host "writing out to $tempfile"
$jsonOutput | out-file $tempfile

#for testing, sleep to 10 seconds
#Start-Sleep -s 10

#remove the *.tmp extension
write-host "renaming $tempfile to $outputfile"

move-item -path $tempfile -destination $outputfile -force
## END OF SCRIPT ###
