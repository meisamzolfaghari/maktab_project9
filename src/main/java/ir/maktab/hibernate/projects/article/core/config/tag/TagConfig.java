package ir.maktab.hibernate.projects.article.core.config.tag;

import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;

import java.util.ArrayList;

public class TagConfig {
    public static void config() {
        TagRepository tagRepository = TagRepository.getInstance();
        if(tagRepository.findAll().isEmpty()) {
            tagRepository.save(new Tag(null , "tag1" , new ArrayList<>()));
            tagRepository.save(new Tag(null , "tag2" , new ArrayList<>()));
            tagRepository.save(new Tag(null , "tag3" , new ArrayList<>()));
            tagRepository.save(new Tag(null , "tag4" , new ArrayList<>()));
            tagRepository.save(new Tag(null , "tag5" , new ArrayList<>()));
        }
    }
}
