package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromNoteEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Note;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import nl.ordina.recruitmentexperience.data.application.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final FromNoteEntityMapper fromNoteEntityMapper;

    private final NoteRepository noteRepository;

    public List<Note> getNotesByApplication(final Long applicationId) {
        List<NoteEntity> noteEntities = noteRepository.findAllByApplication_Id(applicationId);
        return fromNoteEntityMapper.map(noteEntities);
    }
}
