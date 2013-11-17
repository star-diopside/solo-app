package jp.gr.java_conf.star_diopside.solo.data.repository;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザリポジトリインタフェース
 */
public interface UserRepository extends JpaRepository<User, String> {

}
