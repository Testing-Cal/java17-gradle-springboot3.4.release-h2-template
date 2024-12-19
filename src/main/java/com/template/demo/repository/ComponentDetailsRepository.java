package com.template.demo.repository;

import com.template.demo.bean.ComponentDetailsBean;
import com.template.demo.entity.ComponentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentDetailsRepository extends JpaRepository<ComponentDetailsEntity, Long> {

	<S extends ComponentDetailsEntity> S save(final S componentDetails);

	ComponentDetailsEntity findByComponentName(final String componentName);

	ComponentDetailsEntity findByComponentIdentifier(final String componentIdentifier);

	@Query("SELECT new com.template.demo.bean.ComponentDetailsBean(c.componentName,c.componentIdentifier) FROM ComponentDetailsEntity c WHERE c.componentName = (:componentName)")
    ComponentDetailsBean getByComponentName(@Param("componentName") final String componentName);

	@Query("SELECT new com.template.demo.bean.ComponentDetailsBean(c.componentName,c.componentIdentifier) FROM ComponentDetailsEntity c WHERE c.componentName != (:componentName)")
	List<ComponentDetailsBean> getByComponentNameNotIn(@Param("componentName") final String componentName);

}
