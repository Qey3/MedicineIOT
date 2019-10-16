package iot.medicine.sugar;

import iot.medicine.pojo.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SugarService {

    @Autowired
    SugarReposytory reposytory;

    public List<SugarTests> getTests(Long lastId) {
        return reposytory.findTests(lastId);
    }
}
