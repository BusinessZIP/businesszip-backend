package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Sharecard;
import kr.curious.Bzip.repository.SharecardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharecardService {
    private final SharecardRepository sharecardRepository;

    public List<Sharecard> findAllByMemberId(Long id)
    {
        return sharecardRepository.findAllByMemberId(id);
    }
}
