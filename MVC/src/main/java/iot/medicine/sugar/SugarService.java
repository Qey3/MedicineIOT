package iot.medicine.sugar;

import my.entity.mvc.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SugarService {

    @Autowired
    SugarReposytory reposytory;

    public List<SugarTests> getTests(int page) {
        return reposytory.findTests(page);
    }
}
