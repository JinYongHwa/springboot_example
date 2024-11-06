package kr.ac.mjc.blog.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.domain.Category;
import kr.ac.mjc.blog.dto.ArticleDto;
import kr.ac.mjc.blog.repository.BlogRepository;
import kr.ac.mjc.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ArrayList<Article> getArticleList(){
        List<Article> list=blogRepository.findAll();
        return (ArrayList<Article>) list;
    }

    public Article writeArticle(ArticleDto articleDto){
        Category category=categoryRepository.findById(1l).get();

        Article article=new Article(articleDto.getTitle(),articleDto.getContent());
        ArrayList<Category> categoryList=new ArrayList<>();
        categoryList.add(category);
        article.setCategoryList(categoryList);

        article=blogRepository.save(article);
        return article;
    }


    public Article getArticleItem(long id){
        Optional<Article> result=blogRepository.findById(id);
        //결과값이 비어있을때
        if(result.isEmpty()){
            return null;
        }
        //결과값이 있을때
        else{
            return result.get();
        }
        
    }

    public boolean deleteArticle(long id){
        Article article=getArticleItem(id); //글이 있는지 확인
        if(article==null){
            return false;
        }
        blogRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Article modifyArticle(long id,ArticleDto articleDto){
        Optional<Article> result=blogRepository.findById(id);
        if(!result.isEmpty()){
            Article article=result.get();
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
            blogRepository.save(article);
            return article;
        }
        return null;
    }

    public List<Article> getArticleListByCategoryId(long categoryId){
        return blogRepository.findByCategoryList_Id(categoryId);
    }

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

}
