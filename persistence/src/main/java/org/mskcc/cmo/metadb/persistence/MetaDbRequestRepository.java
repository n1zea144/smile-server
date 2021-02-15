package org.mskcc.cmo.metadb.persistence;

import java.util.List;
import org.mskcc.cmo.metadb.model.MetaDbProject;
import org.mskcc.cmo.metadb.model.MetaDbRequest;
import org.mskcc.cmo.metadb.model.MetaDbSample;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ochoaa
 */
@Repository
public interface MetaDbRequestRepository extends Neo4jRepository<MetaDbRequest, Long> {
    @Query("MATCH (r: MetaDbRequest {requestId: $reqId}) RETURN r;")
    MetaDbRequest findByRequestId(@Param("reqId") String reqId);

    @Query("Match (r: MetaDbRequest {requestId: $reqId})-[:HAS_SAMPLE]->"
            + "(s: MetaDbSample) "
            + "RETURN s;")
    List<MetaDbSample> findAllSampleManifests(@Param("reqId") String reqId);

    @Query("MATCH (r: MetaDbRequest {requestId: $reqId}) "
            + "MATCH(r)-[:HAS_SAMPLE]->(sm: MetaDbSample) "
            + "MATCH (sm)<-[:IS_ALIAS]-(s: SampleAlias {toLower(idSource): 'igoid', value: $igoId}) "
            + "RETURN sm")
    MetaDbSample findSampleManifest(@Param("reqId") String reqId, @Param("igoId") String igoId);

    @Query("MATCH (r: MetaDbRequest {requestId: $reqId}) "
            + "MATCH (r)<-[:HAS_REQUEST]-(p: MetaDbProject) "
            + "RETURN p")
    MetaDbProject findMetaDbProject(@Param("reqId") String reqId);
}