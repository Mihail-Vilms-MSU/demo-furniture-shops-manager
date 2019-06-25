package com.mvilms.demo_furniture_shops_manager.web;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

public class PagedResourcesBuilder {

    /**
     *
     * @param page
     * @param assembler
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends ResourceSupport> PagedResources<R> build(Page page, ResourceAssemblerSupport<T, R> assembler){
        List<R> resourceList = (List)page.getContent()
                .stream()
                .map(entry -> assembler.toResource((T) entry))
                .collect(Collectors.toList());

        PagedResources.PageMetadata pageMetadata =
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());

        return new PagedResources<R>(resourceList, pageMetadata);
    }

}
