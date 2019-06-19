package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromNoteEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Note;
import nl.ordina.recruitmentexperience.core.model.NoteId;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final FromNoteEntityMapper fromNoteEntityMapper;

    private final NoteRepository noteRepository;

    private final ApplicationRepository applicationRepository;

    public List<Note> getNotesByApplication(final Long applicationId) {
        List<NoteEntity> noteEntities = noteRepository.findAllByApplication_Id(applicationId);
        return fromNoteEntityMapper.map(noteEntities);
    }

    public Note postNote(final NoteId noteId) {
        final NoteEntity noteEntity = NoteEntity.builder()
                .application(applicationRepository.findOneById(noteId.getApplicationId()))
                .author(noteId.getAuthor())
                .title(noteId.getTitle())
                .text(noteId.getText())
                .creationDate(noteId.getCreationDate().toString())
                .build();

        final NoteEntity savedNote = noteRepository.save(noteEntity);

        return fromNoteEntityMapper.map(savedNote);
    }

    public Note putNote(final NoteId noteId) {
        final NoteEntity noteEntity = noteRepository.findOneById(noteId.getId());

        noteEntity.setText(noteId.getText());
        noteEntity.setTitle(noteId.getTitle());
        noteEntity.setAuthor(noteId.getAuthor());

        final NoteEntity savedNote = noteRepository.save(noteEntity);
        return fromNoteEntityMapper.map(savedNote);
    }
}
