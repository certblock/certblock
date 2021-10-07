package i05.a507.certblock.service;

import i05.a507.certblock.dto.submit.SubmitRes;

import java.util.List;

public interface SubmitService {
    SubmitRes createSubmit(int companyId, int certificateId);
    List<SubmitRes> getSubmits(int companyId);
    List<SubmitRes> createSubmits(int companyId, int[] certIds);
}
