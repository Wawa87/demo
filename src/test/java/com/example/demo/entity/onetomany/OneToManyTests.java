package com.example.demo.entity.onetomany;

import com.example.demo.repository.MakeRepository;
import com.example.demo.repository.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OneToManyTests {
    @Autowired
    MakeRepository makeRepository;
    @Autowired
    ModelRepository modelRepository;

    @BeforeEach
    public void beforeEachTest() {
        makeRepository.deleteAll();
    }

    @Test
    public void testIdNotNullAfterPersist() {
        Make make = new Make();
        make.setName("Ford");

        Model model = new Model();
        model.setName("F150");
        Model model1 = new Model();
        model1.setName("Lightning");
        Model model2 = new Model();
        model2.setName("Mustang");
        List<Model> models = new ArrayList<>();

        models.add(model);
        models.add(model1);
        models.add(model2);

        make.setModels(models);
        makeRepository.save(make);

        Assert.isTrue(make.getId() != null, "Make id != null after persisting to database.");

        Make make1 = makeRepository.findByNameIgnoreCase("ford");

        Assert.isTrue(make1.getId() != null, "Successfully retrieved make from: getByNameIgnoreCase");
    }

    @Test
    public void testChildEntityHasId() {
        Make make = new Make();
        make.setName("Ford");

        Model model = new Model();
        model.setName("F150");
        Model model1 = new Model();
        model1.setName("Lightning");
        Model model2 = new Model();
        model2.setName("Mustang");
        List<Model> models = new ArrayList<>();

        models.add(model);
        models.add(model1);
        models.add(model2);

        make.setModels(models);
        makeRepository.save(make);

        List<Model> models1 = modelRepository.findAll();

        Assert.isTrue(models1.size() == 3, "Test that 3 models were retrieved.");

        models1.forEach((ent)->{
            Assert.isTrue(ent.getId() != null, "Model id != null");
        });
    }

    @Test
    public void testEagerFetch() {
        Make make = new Make();
        make.setName("Ford");

        Model model = new Model();
        model.setName("F150");
        Model model1 = new Model();
        model1.setName("Lightning");
        Model model2 = new Model();
        model2.setName("Mustang");
        List<Model> models = new ArrayList<>();

        models.add(model);
        models.add(model1);
        models.add(model2);

        make.setModels(models);
        makeRepository.save(make);

        Make make1 = makeRepository.findByNameIgnoreCase("ford");
        List<Model> models1 = make1.getModels();

        Assert.isTrue(models1.size() == 3, "Models were fetched by Make through EAGER fetch.");

        models1.forEach((ent)->{
            Assert.isTrue(ent.getId() != null, "Eagerly fetched models have id's.");
        });
    }
}
