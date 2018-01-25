package com.calvo.carolina.repository.db.dao

import android.database.Cursor
import com.calvo.carolina.repository.db.models.ShopDAOModel

class ShopDAO: DAOPersistable<ShopDAOModel>
{
    override fun insert()
    {
    }

    override fun update()
    {
    }

    override fun delete()
    {
    }

    override fun deleteAll()
    {
    }

    override fun query(id: Int): ShopDAOModel
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(): List<ShopDAOModel>
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryCursor(): Cursor
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
