package pl.rstrzalkowski.syllabus.post.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.rstrzalkowski.syllabus.post.application.command.ArchivePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.CreatePostCommand;
import pl.rstrzalkowski.syllabus.post.application.command.UpdatePostCommand;
import pl.rstrzalkowski.syllabus.post.domain.exception.PostNotFoundException;
import pl.rstrzalkowski.syllabus.post.domain.model.Post;
import pl.rstrzalkowski.syllabus.post.domain.repository.PostRepository;
import pl.rstrzalkowski.syllabus.realisation.domain.model.Realisation;
import pl.rstrzalkowski.syllabus.realisation.domain.repository.RealisationRepository;
import pl.rstrzalkowski.syllabus.user.domain.model.User;
import pl.rstrzalkowski.syllabus.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService {

    private final PostRepository postRepository;
    private final RealisationRepository realisationRepository;
    private final UserRepository userRepository;


    //TODO get teacher from context and check if he is the teacher of realisation
    public Post create(CreatePostCommand command) {
        Post post = new Post();
        post.setContent(command.getContent());

        Realisation realisation = realisationRepository.findById(command.getRealisationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        post.setRealisation(realisation);
        post.setTeacher(teacher);
        return postRepository.save(post);
    }

    public Post update(UpdatePostCommand command) {
        Post post = postRepository.findById(command.getId())
                .orElseThrow(PostNotFoundException::new);

        post.setContent(command.getContent() == null ? post.getContent() : command.getContent());
        post.setEdited(true);

        return postRepository.save(post);
    }

    public void archiveById(ArchivePostCommand command) {
        Post post = postRepository.findById(command.id())
                .orElseThrow(PostNotFoundException::new);

        if (post.isArchived()) {
            return;
        }
        post.setArchived(true);
        postRepository.save(post);
    }
}