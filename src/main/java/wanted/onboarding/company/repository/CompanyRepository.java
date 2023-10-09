package wanted.onboarding.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.onboarding.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
