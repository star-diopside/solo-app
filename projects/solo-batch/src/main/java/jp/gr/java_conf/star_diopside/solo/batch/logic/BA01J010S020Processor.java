package jp.gr.java_conf.star_diopside.solo.batch.logic;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.data.repository.UserRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class BA01J010S020Processor implements ItemProcessor<User, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User process(User item) throws Exception {
        User user = userRepository.findOne(item.getUserId());
        return user.getVersion().equals(item.getVersion()) ? user : null;
    }
}
