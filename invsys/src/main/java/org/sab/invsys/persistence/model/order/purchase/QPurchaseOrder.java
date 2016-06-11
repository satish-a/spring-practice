package org.sab.invsys.persistence.model.order.purchase;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QPurchaseOrder is a Querydsl query type for PurchaseOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurchaseOrder extends EntityPathBase<PurchaseOrder> {

    private static final long serialVersionUID = 1482364738;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QPurchaseOrder purchaseOrder = new QPurchaseOrder("purchaseOrder");

    public final SetPath<PurchaseOrderCharges, QPurchaseOrderCharges> charges = this.<PurchaseOrderCharges, QPurchaseOrderCharges>createSet("charges", PurchaseOrderCharges.class, QPurchaseOrderCharges.class);

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> createadBy = createNumber("createadBy", Integer.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<PurchaseOrderItems, QPurchaseOrderItems> items = this.<PurchaseOrderItems, QPurchaseOrderItems>createSet("items", PurchaseOrderItems.class, QPurchaseOrderItems.class);

    public final NumberPath<Integer> modifiedBy = createNumber("modifiedBy", Integer.class);

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> orderDate = createDateTime("orderDate", java.util.Date.class);

    public final StringPath orderId = createString("orderId");

    public final StringPath orderType = createString("orderType");

    public final StringPath referenceNumber = createString("referenceNumber");

    public final SetPath<PurchaseOrderTaxes, QPurchaseOrderTaxes> taxes = this.<PurchaseOrderTaxes, QPurchaseOrderTaxes>createSet("taxes", PurchaseOrderTaxes.class, QPurchaseOrderTaxes.class);

    public final NumberPath<Long> total = createNumber("total", Long.class);

    public final org.sab.invsys.persistence.model.user.QUser user;

    public QPurchaseOrder(String variable) {
        this(PurchaseOrder.class, forVariable(variable), INITS);
    }

    public QPurchaseOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurchaseOrder(PathMetadata<?> metadata, PathInits inits) {
        this(PurchaseOrder.class, metadata, inits);
    }

    public QPurchaseOrder(Class<? extends PurchaseOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.sab.invsys.persistence.model.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

