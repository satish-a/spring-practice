package org.sab.invsys.persistence.model.product;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QProductGroup is a Querydsl query type for ProductGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductGroup extends EntityPathBase<ProductGroup> {

    private static final long serialVersionUID = 1621585841;

    public static final QProductGroup productGroup = new QProductGroup("productGroup");

    public final NumberPath<Integer> createadBy = createNumber("createadBy", Integer.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath groupName = createString("groupName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> modifiedBy = createNumber("modifiedBy", Integer.class);

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final SetPath<Product, QProduct> product = this.<Product, QProduct>createSet("product", Product.class, QProduct.class);

    public QProductGroup(String variable) {
        super(ProductGroup.class, forVariable(variable));
    }

    public QProductGroup(Path<? extends ProductGroup> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QProductGroup(PathMetadata<?> metadata) {
        super(ProductGroup.class, metadata);
    }

}

