package maxxsoft.conversorCSV.aplication.service;

import maxxsoft.conversorCSV.aplication.entities.dto.request.CollaboratorRequest;
import maxxsoft.conversorCSV.aplication.entities.dto.response.CollaboratorResponse;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionCollaboratorParams;
import maxxsoft.conversorCSV.aplication.service.conversion.ConversionSummaryParams;

import java.io.IOException;
import java.util.List;

public interface CollaboratorService {
    CollaboratorResponse createCollaborator(CollaboratorRequest collaborator);
    List<CollaboratorResponse>findAllCollaborator();
    void converterForCsv(ConversionCollaboratorParams params) throws IOException;
}
