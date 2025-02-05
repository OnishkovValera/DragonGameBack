package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.response.RequestDto;
import org.onishkoff.itmo.IS1.exception.AdminRequestAlreadyExists;
import org.onishkoff.itmo.IS1.exception.AdminRequestNotFoundException;
import org.onishkoff.itmo.IS1.model.AdminRequests;
import org.onishkoff.itmo.IS1.model.User;
import org.onishkoff.itmo.IS1.model.enums.Role;
import org.onishkoff.itmo.IS1.model.enums.Status;
import org.onishkoff.itmo.IS1.repository.RequestRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {


    private final UserService userService;
    private final RequestRepository requestRepository;
    private final SecurityUtil securityUtil;
    private final Mapper mapper;

    public void addNewRequest() {
        User user = securityUtil.getUserFromContext();
        Long id = user.getId();
        if (requestRepository.findByUser(user).isEmpty()) {
            AdminRequests adminRequests = AdminRequests.builder()
                    .user(userService.findById(id))
                    .status(Status.PENDING)
                    .requestDate(new Date())
                    .build();

            requestRepository.save(adminRequests);
        }else{
            throw new AdminRequestAlreadyExists();
        }
    }

    public void handleRequest(Long id, Status status) {
        User user = securityUtil.getUserFromContext();
        AdminRequests adminRequests = requestRepository.findById(id).orElseThrow(AdminRequestNotFoundException::new);
        adminRequests.setStatus(status);
        adminRequests.getUser().setRole(adminRequests.getStatus() == Status.APPROVED ? Role.ADMIN : Role.USER);
        adminRequests.setAdminProcessedId(user);
        adminRequests.setProcessedDate(new Date());
        requestRepository.save(adminRequests);
    }

    public List<RequestDto> getAllRequests() {
        return requestRepository.findAll().stream().map(mapper::toAdminRequest).toList();
    }

    public RequestDto getRequest() {
        return mapper.toAdminRequest(requestRepository.findByUser((securityUtil.getUserFromContext())).orElseThrow(AdminRequestNotFoundException::new));
    }
}
