package iotms.medicine.sugar.controller;

import my.entity.sugarMS.RowSugarData;
import iotms.medicine.sugar.service.SugarTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SugarTestsController {

    @Autowired
    SugarTestsService sugarTestsService;

    @PostMapping("/send-glucose-analysis")
    public void saveGlucose(@RequestBody RowSugarData rowSugarData){
        sugarTestsService.saveAnalysis(rowSugarData);
    }

}
