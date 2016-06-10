package org.sab.invsys.persistence.model.order.purchase;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QPurchaseOrderTaxes is a Querydsl query type for PurchaseOrderTaxes
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurchaseOrderTaxes extends EntityPathBase<PurchaseOrderTaxes> {

    private static final long serialVersionUID = 1567145847;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QPurchaseOrderTaxes purchaseOrderTaxes = new QPurchaseOrderTaxes("purchaseOrderTaxes");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPurchaseOrder po;

    public final StringPath taxName = createString("taxName");

    public QPurchaseOrderTaxes(String variable) {
        this(PurchaseOrderTaxes.class, forVariable(variable), INITS);
    }

    public QPurchaseOrderTaxes(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurchaseOrderTaxes(PathMetadata<?> metadata, PathInits inits) {
        this(PurchaseOrderTaxes.class, metadata, inits);
    }

    public QPurchaseOrderTaxes(Class<? extends PurchaseOrderTaxes> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.po = inits.isInitialized("po") ? new QPurchaseOrder(forProperty("po"), inits.get("po")) : null;
    }

}

