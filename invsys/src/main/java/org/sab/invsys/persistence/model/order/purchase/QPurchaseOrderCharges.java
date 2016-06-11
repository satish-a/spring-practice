package org.sab.invsys.persistence.model.order.purchase;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QPurchaseOrderCharges is a Querydsl query type for PurchaseOrderCharges
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurchaseOrderCharges extends EntityPathBase<PurchaseOrderCharges> {

    private static final long serialVersionUID = 765486749;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QPurchaseOrderCharges purchaseOrderCharges = new QPurchaseOrderCharges("purchaseOrderCharges");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath chargeName = createString("chargeName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPurchaseOrder po;

    public QPurchaseOrderCharges(String variable) {
        this(PurchaseOrderCharges.class, forVariable(variable), INITS);
    }

    public QPurchaseOrderCharges(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurchaseOrderCharges(PathMetadata<?> metadata, PathInits inits) {
        this(PurchaseOrderCharges.class, metadata, inits);
    }

    public QPurchaseOrderCharges(Class<? extends PurchaseOrderCharges> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.po = inits.isInitialized("po") ? new QPurchaseOrder(forProperty("po"), inits.get("po")) : null;
    }

}

