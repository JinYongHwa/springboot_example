package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.domain.Article;
import kr.ac.mjc.blog.dto.AddArticleRequest;
import kr.ac.mjc.blog.dto.ArticleResponse;
import kr.ac.mjc.blog.dto.UpdateArticleRequest;
import kr.ac.mjc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request, @SessionAttribute(name="userId",required=false)String userId, HttpServletRequest httpServletRequest){
        ArticleResponse response=new ArticleResponse();
        if(userId==null){
            response.setSuccess(false);
            response.setMessage("로그인된 사용자만 작성가능합니다");
            return ResponseEntity.ok(response);
        }


        Article savedArticle=blogService.save(request,userId);
        response.setSuccess(true);
        response.setMessage("글작성이 완료되었습니다");
        response.setArticle(savedArticle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> findAllArticle(){
        List<Article> articles=blogService.findAll();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable long id){
        Article article=blogService.findOne(id);
        return ResponseEntity.ok().body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(
            @PathVariable long id,
            @RequestBody UpdateArticleRequest updateArticle){
        Article article=blogService.update(id,updateArticle);
        return ResponseEntity.ok().body(article);
    }
}
