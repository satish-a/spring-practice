package org.sab.invsys.persistence.model.order.purchase;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QPurchaseOrderItems is a Querydsl query type for PurchaseOrderItems
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPurchaseOrderItems extends EntityPathBase<PurchaseOrderItems> {

    private static final long serialVersionUID = 1557535134;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QPurchaseOrderItems purchaseOrderItems = new QPurchaseOrderItems("purchaseOrderItems");

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPurchaseOrder po;

    public final org.sab.invsys.persistence.model.product.QProduct product;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final NumberPath<Integer> unitPrice = createNumber("unitPrice", Integer.class);

    public QPurchaseOrderItems(String variable) {
        this(PurchaseOrderItems.class, forVariable(variable), INITS);
    }

    public QPurchaseOrderItems(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPurchaseOrderItems(PathMetadata<?> metadata, PathInits inits) {
        this(PurchaseOrderItems.class, metadata, inits);
    }

    public QPurchaseOrderItems(Class<? extends PurchaseOrderItems> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.po = inits.isInitialized("po") ? new QPurchaseOrder(forProperty("po"), inits.get("po")) : null;
        this.product = inits.isInitialized("product") ? new org.sab.invsys.persistence.model.product.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

