package jp.gr.java_conf.star_diopside.solo.batch.logic;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.service.logic.auth.UserManager;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class BA01J010S010Processor implements ItemProcessor<User, User> {

    @Autowired
    private UserManager userManager;

    @Override
    public User process(User item) throws Exception {

        // 無効ユーザの場合に処理対象とする。
        if (userManager.checkValid(item)) {
            return null;
        }

        return item;
    }
}
