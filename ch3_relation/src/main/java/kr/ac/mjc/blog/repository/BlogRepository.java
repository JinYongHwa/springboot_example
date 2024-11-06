package kr.ac.mjc.blog.repository;

import kr.ac.mjc.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Article,Long> {

    List<Article> findByCategoryList_Id(Long categoryId);

}
