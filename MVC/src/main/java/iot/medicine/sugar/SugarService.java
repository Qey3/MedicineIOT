package iot.medicine.sugar;

import iot.medicine.device.DevicesService;
import iot.medicine.user.UserService;
import my.entity.mvc.Chart;
import my.entity.mvc.SugarTestsMVC;
import my.entity.mvc.device.Device;
import my.entity.mvc.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional(value = "transactionManager")
public class SugarService {

    private static final Logger log = Logger.getLogger("SugarService");

    @Autowired
    SugarRepository repository;

    @Autowired
    DevicesService devicesService;

    @Autowired
    UserService userService;

    public List<SugarTestsMVC> getTests(int page, String userName, String search) {

        Double glucose;
        try {
            glucose = Double.parseDouble(search.trim());
            glucose = glucose > 0 ? glucose : 0D;
        }catch (NumberFormatException nfe){
            glucose = 0D;
        }

//        List<Device> userDevices = devicesService.getUserDevices(userName);
//
//        List<SugarTestsMVC> sugar = new ArrayList<>();
//
//        for(Device device : userDevices){
//            sugar.addAll(repository.getAllSugarTestsByDevice(device, glucose));
//        }
        return repository.findTests(page, userName, glucose);
    }

    public List<SugarTestsMVC> getAdminTests(int page) {

        int maxResult = 20;
        page = page > 1 ? (page -1) * 20: 0;

        List<Users> users = userService.getAllUsers();

        List<SugarTestsMVC> sugar = new ArrayList<>();

        for(Users user : users){
            sugar.add(repository.findLastUserTest(user));
        }

        return sugar.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(SugarTestsMVC::getAnalysisTime)
                .reversed())
                .skip(page)
                .limit(maxResult)
                .collect(Collectors.toList());
    }

    public List<Chart> getChartsForUser(String userName) {

        List<Chart> charts = new ArrayList<>();

        charts.add(new Chart("0 - 3.2 (Low)", repository.getUserChartTests(userName, 0D, 3.2)));
        charts.add(new Chart("3,3-5,5 (Normal)", repository.getUserChartTests(userName, 3.3, 5.5)));
        charts.add(new Chart("5.6 - 11,1 (High)", repository.getUserChartTests(userName, 5.6, 11.1)));
        charts.add(new Chart("11,2 - 50 (Extreme)", repository.getUserChartTests(userName, 11.2, 50D)));

        return charts;
    }
}
