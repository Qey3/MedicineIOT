package iot.medicine.copydatabase;

import iot.medicine.device.DevicesRepository;
import my.entity.mvc.LastId;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.sugarMS.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CopySugarService {

    private static Logger log = Logger.getLogger("CopySugarService");

    @Autowired
    CopySugarRepo copySugarRepo;

    @Autowired
    DevicesRepository devicesRepository;


    @Scheduled(cron = "0/50 * * * * *")
//    @Scheduled(fixedDelay = 30000)
    public void executeCopy() {
        log.info("do new copy");
        Long last_id = copySugarRepo.getLastId();
        log.info("old last_id = " + last_id);
        List<SugarTests> sugarTests = copySugarRepo.getNewSugarTests(last_id);

        log.info("sugarTestSize = " + sugarTests.size());

        if (sugarTests.size() > 0) {
            log.info("sugarTestSize > 0 ");
            List<SugarTestsMVC> sugarTestsMVC = sugarTests.stream()
                    .map(sugarTests1 -> new SugarTestsMVC(sugarTests1.getId(),
                            sugarTests1.getAnalysisTime(),
                            sugarTests1.getGlucose(),
                            devicesRepository.getDeviceBySerialNumber(sugarTests1.getDevice())))
                    .collect(Collectors.toList());

            log.info("sugarTestMVCSize = " + sugarTestsMVC.size());

            copySugarRepo.copySugarTests(sugarTestsMVC);

            try {
                last_id = sugarTests.stream()
                        .map(SugarTests::getId)
                        .max(Long::compareTo)
                        .get();
            } catch (Exception e) {
                log.warning("error " + e.getMessage());
            }
            log.info(" new last_id = " + last_id);
            copySugarRepo.saveLastId(new LastId(1L, last_id));
        }

    }
}
