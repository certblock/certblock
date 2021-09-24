package i05.a507.certblock.service;

import i05.a507.certblock.domain.University;
import i05.a507.certblock.repository.UniversityRepository;
import org.springframework.stereotype.Service;

@Service("universityService")
public class UniversityServiceImpl implements UniversityService {

	UniversityRepository universityRepository;

	@Override
	public University getUniversity(int universityId) {
		return universityRepository.findByUniversityId(universityId).orElse(null);
	}


}
