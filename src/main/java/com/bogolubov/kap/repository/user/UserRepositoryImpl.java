package com.bogolubov.kap.repository.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import com.bogolubov.kap.dao.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public Page<UserDtoForPageable> getPageable(UserFilterContainer container) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserDtoForPageable> query = cb.createQuery(UserDtoForPageable.class);
        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<User> countRoot = query.from(User.class);
        Predicate[] predicates = createPredicateForPageable(cb, countRoot, container);
        Long count = em.createQuery(queryCount.select(cb.count(countRoot)).where(predicates)).getSingleResult();
        Root<User> root = query.from(User.class);

        Selection<UserDtoForPageable> selection = cb.construct(UserDtoForPageable.class,
                root.get("id"),
                root.get("login"),
                root.get("email"),
                root.get("fullName"),
                root.get("enabled"),
                root.join("position", JoinType.LEFT),
                root.join("department", JoinType.LEFT)
        );

        return new PageImpl<>(
                em.createQuery(query.select(selection).where(predicates))
                        .setFirstResult(container.getPageable().getOffset())
                        .setMaxResults(container.getPageable().getPageSize())
                        .getResultList(),
                container.getPageable(),
                count);
    }

    private Predicate[] createPredicateForPageable(CriteriaBuilder criteriaBuilder, Root<User> root, UserFilterContainer container) {
        List<Predicate> predicates = new ArrayList<>();
        if (container.getLogin() != null && !container.getLogin().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("login")), "%" + container.getLogin().toLowerCase() + "%"));
        }
        if (container.getEmail() != null && !container.getEmail().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + container.getEmail().toLowerCase() + "%"));
        }
        if (container.getFullName() != null && !container.getFullName().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + container.getFullName().toLowerCase() + "%"));
        }
        if (container.getEnabled() != null) {
            predicates.add(criteriaBuilder.equal(root.get("enabled"), container.getEnabled()));
        }
        if (container.getPosition() != null) {
            predicates.add(criteriaBuilder.equal(root.join("position", JoinType.LEFT).get("id"), container.getPosition().getId()));
        }

        if (container.getDepartment() != null) {
            predicates.add(criteriaBuilder.equal(root.join("department", JoinType.LEFT).get("id"), container.getDepartment().getId()));
        }

        return predicates.toArray(new Predicate[0]);
    }
}
