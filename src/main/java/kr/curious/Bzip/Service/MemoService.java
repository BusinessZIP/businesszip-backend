package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Memo;
import kr.curious.Bzip.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public Memo save(Memo memo) { return memoRepository.save(memo); }
    public Optional<Memo> findByMemberIdAndCard_Id(Long memberId, Long cardId)
    {
        return memoRepository.findByMemberIdAndCard_Id(memberId, cardId);
    }
}
