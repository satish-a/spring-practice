package org.sab.invsys.persistence.model.order.sales;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrder> {

    private static final long serialVersionUID = -742779866;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final SetPath<SalesOrderCharges, QSalesOrderCharges> charges = this.<SalesOrderCharges, QSalesOrderCharges>createSet("charges", SalesOrderCharges.class, QSalesOrderCharges.class);

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> createadBy = createNumber("createadBy", Integer.class);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Integer> discount = createNumber("discount", Integer.class);

    public final DateTimePath<java.util.Date> dueDate = createDateTime("dueDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<SalesOrderItems, QSalesOrderItems> items = this.<SalesOrderItems, QSalesOrderItems>createSet("items", SalesOrderItems.class, QSalesOrderItems.class);

    public final NumberPath<Integer> modifiedBy = createNumber("modifiedBy", Integer.class);

    public final DateTimePath<java.util.Date> modifiedDate = createDateTime("modifiedDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> orderDate = createDateTime("orderDate", java.util.Date.class);

    public final StringPath orderId = createString("orderId");

    public final StringPath orderType = createString("orderType");

    public final StringPath referenceNumber = createString("referenceNumber");

    public final SetPath<SalesOrderTaxes, QSalesOrderTaxes> taxes = this.<SalesOrderTaxes, QSalesOrderTaxes>createSet("taxes", SalesOrderTaxes.class, QSalesOrderTaxes.class);

    public final NumberPath<Long> total = createNumber("total", Long.class);

    public final org.sab.invsys.persistence.model.user.QUser user;

    public QSalesOrder(String variable) {
        this(SalesOrder.class, forVariable(variable), INITS);
    }

    public QSalesOrder(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrder(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrder.class, metadata, inits);
    }

    public QSalesOrder(Class<? extends SalesOrder> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.sab.invsys.persistence.model.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

