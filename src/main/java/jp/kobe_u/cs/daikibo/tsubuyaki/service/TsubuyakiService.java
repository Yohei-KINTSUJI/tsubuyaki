package jp.kobe_u.cs.daikibo.tsubuyaki.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.repository.TsubuyakiRepository;

@Service
public class TsubuyakiService {
    @Autowired
    private
    TsubuyakiRepository repo; // レポジトリ
    // つぶやきを投稿
    public Tsubuyaki postTsubuyaki(Tsubuyaki t) {
        //名前がない場合の業務ロジック
        String name = t.getName();
        if (name==null || name.length()==0) {
            t.setName("名無しさん");
        }
        t.setCreatedAt(new Date());  //作成日時をセット
        return getRepo().save(t); //セーブしたオブジェクトを返却
    }
    public TsubuyakiRepository getRepo() {
        return repo;
    }
    public void setRepo(TsubuyakiRepository repo) {
        this.repo = repo;
    }
    // 全つぶやきを取得
    public List<Tsubuyaki> getAllTsubuyaki() {
        Iterable<Tsubuyaki> found = getRepo().findAll();
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }
    // つぶやきを検索
    public List<Tsubuyaki> searchTsubuyaki(String keyword) {
        List<Tsubuyaki> found = getRepo().findByCommentContaining(keyword);
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }

 }