package org.sab.invsys.persistence.model.order.sales;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QSalesOrderCharges is a Querydsl query type for SalesOrderCharges
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSalesOrderCharges extends EntityPathBase<SalesOrderCharges> {

    private static final long serialVersionUID = -113647303;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QSalesOrderCharges salesOrderCharges = new QSalesOrderCharges("salesOrderCharges");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath chargeName = createString("chargeName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSalesOrder so;

    public QSalesOrderCharges(String variable) {
        this(SalesOrderCharges.class, forVariable(variable), INITS);
    }

    public QSalesOrderCharges(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSalesOrderCharges(PathMetadata<?> metadata, PathInits inits) {
        this(SalesOrderCharges.class, metadata, inits);
    }

    public QSalesOrderCharges(Class<? extends SalesOrderCharges> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.so = inits.isInitialized("so") ? new QSalesOrder(forProperty("so"), inits.get("so")) : null;
    }

}

