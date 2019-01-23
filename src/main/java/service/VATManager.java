package service;

import enums.SiteMap;
import enums.VATType;
import model.VATRecord;
import model.VATRecordList;
import org.apache.log4j.Logger;
import utils.VATRecordComparators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Singleton class for handling VAT records
 */
public class VATManager {

    final Logger log = Logger.getLogger(VATManager.class);
    private static VATManager instance;

    public static VATManager getInstance(){
        if(instance == null){
            instance = new VATManager();
        }
        return instance;
    }

    /**
     *
     * @param count number of demanded records (must be >= 1).
     * @return String representations of VAT records with highest standard VAT rate.
     */
    public List<String> getTopStandardVATRateRecords(int count){
        List<VATRecord> vatRecordList = getStandardVATRateRecordsFromSortedList(count, false);
        return getMsgList(vatRecordList);
    }

    /**
     *
     * @param count number of demanded records (must be >= 1).
     * @return String representations of VAT records with lowest standard VAT rate.
     */
    public List<String> getBottomStandardVATRateRecords(int count){
        List<VATRecord> vatRecordList = getStandardVATRateRecordsFromSortedList(count, true);
        return getMsgList(vatRecordList);
    }

    /**
     *
     * @param count number of demanded records (must be >= 1).
     * @param desc defines list sort order (true = descending)
     * @return Number of VATRecords depending on count parameter.
     */
    private List<VATRecord> getStandardVATRateRecordsFromSortedList(int count, boolean desc){
        if(count < 1){
            log.error("Unexpected method call [service.VATManager.getTopStandardVATRateRecords] parameter must be 1 or higher.");
            return null;
        }
        VATRecordList vatRecordList = VATRateService.getInstance().getVATRecordList(SiteMap.VAT_JSONVAT_COM);
        if(vatRecordList == null || vatRecordList.getRates() == null){
            return null;
        }

        if(desc){
            Collections.sort(vatRecordList.getRates(), new VATRecordComparators.StandardVATRate());
        } else {
            Collections.sort(vatRecordList.getRates(), new VATRecordComparators.StandardVATRateDesc());
        }
        if(count > vatRecordList.getRates().size()){
            log.warn("Unexpected method call [service.VATManager.getTopStandardVATRateRecords] parameter should be same or lower then: " + vatRecordList.getRates().size());
            return vatRecordList.getRates().subList(0, vatRecordList.getRates().size());
        }
        return vatRecordList.getRates().subList(0, count);
    }

    public List<String> getMsgList(List<VATRecord> vatRecordList){
        List<String> resultList = new LinkedList<>();
        if(vatRecordList == null){
            return null;
        }
        for(VATRecord vatRecord : vatRecordList){
            resultList.add(getRecordMsg(vatRecord));
        }
        return resultList;
    }

    public String getRecordMsg(VATRecord vatRecord){
        return vatRecord == null ? "Error" : vatRecord.toString() + ", standard VAT rate='" + vatRecord.getVATValue(VATType.STANDARD) + "'";
    }

}
