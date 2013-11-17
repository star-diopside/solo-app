package jp.gr.java_conf.star_diopside.solo.data.repository;

import jp.gr.java_conf.star_diopside.solo.data.entity.Authority;
import jp.gr.java_conf.star_diopside.solo.data.entity.AuthorityPk;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 権限リポジトリインタフェース
 */
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityPk> {

}
