package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Company;
import i05.a507.certblock.domain.Submit;
import i05.a507.certblock.dto.submit.SubmitRes;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.repository.CompanyRepository;
import i05.a507.certblock.repository.SubmitRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmitServiceImpl implements SubmitService {
    private final CompanyRepository companyRepository;
    private final CertificateRepository certificateRepository;
    private final SubmitRepository submitRepository;

    @Override
    public SubmitRes createSubmit(int companyId, int certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId).orElseThrow(RuntimeException::new);
        Company company = companyRepository.findById(companyId).orElseThrow(RuntimeException::new);
        Submit submit = new Submit(certificate, company);
        submit = submitRepository.save(submit);
        return SubmitRes.of(submit);
    }

    @Override
    public List<SubmitRes> getSubmits(int companyId) {
        List<Submit> submits = submitRepository.findByCompanyId(companyId);
        return submits.stream().map(SubmitRes::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SubmitRes> createSubmits(int companyId, int[] certIds) {
        Company company = companyRepository.findById(companyId).orElseThrow(RuntimeException::new);

        List<SubmitRes> submitResList = new ArrayList<>();
        List<Integer> certIdList = Arrays.stream(certIds).boxed().collect(Collectors.toList());
        for (Certificate certificate : certificateRepository.findByIds(certIdList)) {
            Submit submit = submitRepository.save(new Submit(certificate, company));
            submitResList.add(SubmitRes.of(submit));
        }
        return submitResList;
    }
}
