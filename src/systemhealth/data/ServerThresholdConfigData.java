/**
 *
 */
package systemhealth.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 1062992
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerThresholdConfigData {

    // @XmlElementWrapper(name = "thresholds")
    @XmlElement(name = "serverThreshold")
    private List<ServerThreshold> thresholds = new ArrayList<ServerThreshold>();

    /**
     * @return the thresholds
     */
    public List<ServerThreshold> getThresholds() {
        return thresholds;
    }

    /**
     * @param thresholds
     *            the thresholds to set
     */
    public void setThresholds(List<ServerThreshold> thresholds) {
        this.thresholds = thresholds;
    }

}
