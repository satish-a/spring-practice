package org.sab.invsys.persistence.model.order.sales;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QSalesOrderItems is a Querydsl query type for SalesOrderItems
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrderItems extends EntityPathBase<SalesOrderItems> {

    private static final long serialVersionUID = -695891142;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QSalesOrderItems salesOrderItems = new QSalesOrderItems("salesOrderItems");

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final org.sab.invsys.persistence.model.product.QProduct product;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final QSalesOrder so;

    public final NumberPath<Integer> total = createNumber("total", Integer.class);

    public final NumberPath<Integer> unitPrice = createNumber("unitPrice", Integer.class);

    public QSalesOrderItems(String variable) {
        this(SalesOrderItems.class, forVariable(variable), INITS);
    }

    public QSalesOrderItems(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrderItems(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrderItems.class, metadata, inits);
    }

    public QSalesOrderItems(Class<? extends SalesOrderItems> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new org.sab.invsys.persistence.model.product.QProduct(forProperty("product"), inits.get("product")) : null;
        this.so = inits.isInitialized("so") ? new QSalesOrder(forProperty("so"), inits.get("so")) : null;
    }

}

