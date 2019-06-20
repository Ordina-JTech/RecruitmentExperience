package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.DocumentIdModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Document;
import org.springframework.stereotype.Component;

@Component
public class ToDocumentIdModelMapper implements Mapper<Document, DocumentIdModel> {

       @Override
    public DocumentIdModel map(Document input) {
           final DocumentIdModel documentIdModel = new DocumentIdModel();
           documentIdModel.setId(input.getId());
           documentIdModel.setApplicationId(input.getApplication().getId());
           documentIdModel.setCreationDate(input.getCreationDate());
           documentIdModel.setTitle(input.getTitle());
           documentIdModel.setExtension(input.getExtension());

           return documentIdModel;
    }
}
