package utils;

import enums.VATType;
import model.VATRecord;
import org.apache.log4j.Logger;

import java.util.Comparator;

public class VATRecordComparators {
    public static class StandardVATRate implements Comparator<VATRecord>{
        final Logger log = Logger.getLogger(StandardVATRate.class);
        @Override
        public int compare(VATRecord o1, VATRecord o2) {
            if(o1.getVATValue(VATType.STANDARD) == null){
                log.warn("In VATRecord missing standard VAT rate. " + o1.toString());
                return -1;
            }
            if(o2.getVATValue(VATType.STANDARD) == null){
                 log.warn("In VATRecord missing standard VAT rate. " + o2.toString());
                return 1;
            }
            return o1.getVATValue(VATType.STANDARD).compareTo(o2.getVATValue(VATType.STANDARD));
        }
    }

    /**
     *
     */
    public static class StandardVATRateDesc implements Comparator<VATRecord>{
        final Logger log = Logger.getLogger(StandardVATRateDesc.class);
        @Override
        public int compare(VATRecord o1, VATRecord o2) {
            if(o1.getVATValue(VATType.STANDARD) == null){
                log.warn("In VATRecord missing standard VAT rate. " + o1.toString());
                return 1;
            }
            if(o2.getVATValue(VATType.STANDARD) == null){
                log.warn("In VATRecord missing standard VAT rate. " + o2.toString());
                return -1;
            }
            return -1 * o1.getVATValue(VATType.STANDARD).compareTo(o2.getVATValue(VATType.STANDARD));
        }
    }
}