package org.mskcc.cmo.metadb.service;

import java.util.UUID;
import org.mskcc.cmo.metadb.model.MetadbPatient;

public interface MetadbPatientService {
    MetadbPatient savePatientMetadata(MetadbPatient patient);
    MetadbPatient getPatientByCmoPatientId(String cmoPatientId);
    UUID getPatientIdBySample(UUID metadbSampleId);
    MetadbPatient updateCmoPatientId(String oldCmoPatientId, String newCmoPatientId);
    void deletePatient(MetadbPatient patient);
}
