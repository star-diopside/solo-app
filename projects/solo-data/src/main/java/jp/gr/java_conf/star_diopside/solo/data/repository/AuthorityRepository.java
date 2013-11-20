package jp.gr.java_conf.star_diopside.solo.data.repository;

import java.util.List;

import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
import jp.gr.java_conf.star_diopside.solo.data.entity.AuthorityPk;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 権限リポジトリインタフェース
 */
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityPk> {

    /**
     * ユーザIDを条件に権限情報を検索する。
     * 
     * @param userId ユーザID
     * @return 権限情報一覧
     */
    List<Authority> findByUserId(String userId);

}
