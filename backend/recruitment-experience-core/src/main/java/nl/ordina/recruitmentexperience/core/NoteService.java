package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final ApplicationRepository applicationRepository;

    public List<NoteEntity> getNotesByApplication(final Long applicationId) {
        return noteRepository.findAllByApplication_Id(applicationId);
    }

    public NoteEntity postNote(final NoteEntity noteEntity) {
        final NoteEntity noteEntityNew = NoteEntity.builder()
                //.application(applicationRepository.findOneById(noteEntity.getApplicationId()))
                .author(noteEntity.getAuthor())
                .title(noteEntity.getTitle())
                .text(noteEntity.getText())
                .creationDate(noteEntity.getCreationDate().toString())
                .build();
        return noteRepository.save(noteEntityNew);
    }

    public NoteEntity putNote(final NoteEntity noteEntity) {
        final NoteEntity noteEntityFromDb = noteRepository.findOneById(noteEntity.getId());

        noteEntityFromDb.setText(noteEntity.getText());
        noteEntityFromDb.setTitle(noteEntity.getTitle());
        noteEntityFromDb.setAuthor(noteEntity.getAuthor());

        return noteRepository.save(noteEntityFromDb);
    }
}
