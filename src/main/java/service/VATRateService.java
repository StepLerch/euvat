package service;

import enums.SiteMap;
import model.VATRecordList;

public class VATRateService {

    private static VATRateService instance;

    public static VATRateService getInstance(){
        if(instance == null){
            instance = new VATRateService();
        }
        return instance;
    }

    public VATRecordList getVATRecordList(SiteMap site) {
        return JsonService.getObjectFromUrl(VATRecordList.class, site.getUrl());
    }

}
