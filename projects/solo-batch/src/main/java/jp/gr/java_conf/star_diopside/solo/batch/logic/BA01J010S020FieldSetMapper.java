package jp.gr.java_conf.star_diopside.solo.batch.logic;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class BA01J010S020FieldSetMapper implements FieldSetMapper<User> {

    @Override
    public User mapFieldSet(FieldSet fieldSet) throws BindException {
        User user = new User();
        user.setUserId(fieldSet.readString("userId"));
        user.setVersion(fieldSet.readInt("version"));
        return user;
    }
}
