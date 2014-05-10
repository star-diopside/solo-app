package jp.gr.java_conf.star_diopside.solo.batch.logic;

import java.util.List;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class BA01J010S020Writer implements ItemWriter<User> {

    @Autowired
    private UserManager userManager;

    @Override
    public void write(List<? extends User> items) throws Exception {
        items.forEach(user -> userManager.removeUser(user));
    }
}
