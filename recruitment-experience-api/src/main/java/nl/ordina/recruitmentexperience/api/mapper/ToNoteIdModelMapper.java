package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Note;
import org.springframework.stereotype.Component;

@Component
public class ToNoteIdModelMapper implements Mapper<Note, NoteIdModel> {

    @Override
    public NoteIdModel map(Note input) {
        final NoteIdModel noteIdModel = new NoteIdModel();
        noteIdModel.setId(input.getId());
        noteIdModel.setAuthor(input.getAuthor());
        noteIdModel.setTitle(input.getTitle());
        noteIdModel.setText(input.getText());
        noteIdModel.setCreationDate(input.getCreationDate());
        noteIdModel.setApplicationId(input.getApplication().getId());
        return noteIdModel;
    }
}
