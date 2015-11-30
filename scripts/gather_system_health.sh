#!/bin/bash

# this script is intended to be executed periodically to write out the server performance stats.
#
# format is json:
# 
#{
#     "serverName": "AURD-AI168889",
#     "disks": [
#         {"deviceID": "/home/phat/", "totalSizeKB": 488384532, "freeSizeKB": 420383844, "percentDiskFree": 86.08},
#         {"deviceID": "G:", "totalSizeKB": 219343512, "freeSizeKB": 15145880, "percentDiskFree": 6.91},
#         {"deviceID": "H:", "totalSizeKB": 3140961984, "freeSizeKB": 45866296, "percentDiskFree": 1.46},
#         {"deviceID": "Z:", "totalSizeKB": 41941980, "freeSizeKB": 13982724, "percentDiskFree": 33.34}
#     ],
#     "serverUpTimeDays": 0.863,
#     "percentCPUUsage": 5.497
#}
#

serverName=`hostname`
#echo "serverName = $serverName"
echo -e "{\n\t\"serverName\": \"$serverName\","
echo -e "\t\"disks\": ["

#get all mounted disk that are of type [ext*, cifs] - cifs is samba network shares
disks=`df -kT`

echo "$disks" | grep -E 'ext|cifs' > tmpdisk.out
numberLine=`cat tmpdisk.out|wc -l`

#initialize counter
counter=1
while IFS=' ' read -r f1 f2 f3 f4 f5 f6 f7
do
#    echo "field1: $f1, field2: $f2, field3: $f3, field4: $f4, field5: $f5, field6: $f6, field7: $f7"
    #get the drive mount point
    diskid=$f7
    #get the total drive space size
    disksize=$f3
    #get the free drive space
    diskfree=$f5
    
    # calculate the percent drive space free
    diskpercentfree=`echo "scale=2;$diskfree/$disksize*100.0" | bc`
    
    if [ "$counter" = "$numberLine" ]; then
        echo -e "\t\t{\"deviceID\": \"$diskid\", \"totalSizeKB\": $disksize, \"freeSizeKB\": $diskfree, \"percentDiskFree\": $diskpercentfree}"
    else
        echo -e "\t\t{\"deviceID\": \"$diskid\", \"totalSizeKB\": $disksize, \"freeSizeKB\": $diskfree, \"percentDiskFree\": $diskpercentfree},"
    fi

    ((counter++))
done < tmpdisk.out
#close the disks array using ']'
echo -e "\t],"

#gather server up time
uptime=`uptime`
serverUpTimeDays=`echo $uptime|awk '{print $3}'`
echo -e "\t\"serverUpTimeDays\": $serverUpTimeDays,"

#get cpu percent usage
cpuPercentUsage=`top -bn 2 -d 0.01 | grep '^%Cpu' | tail -n 1 | gawk '{print $2+$4+$6}'`
echo -e "\t\"percentCPUUsage\": $cpuPercentUsage\n}"

