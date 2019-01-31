package ua.meugen.android.pdfcomposer.ui.activities.base.fragment.binding

import android.support.v4.util.SparseArrayCompat
import android.view.View

import java.lang.ref.WeakReference

/**
 * @author meugen
 */
abstract class BaseBinding {

    private var rootViewRef: WeakReference<View>? = null
    private var childrenViewRefs: SparseArrayCompat<WeakReference<View>>? = null

    fun attachView(view: View) {
        rootViewRef = WeakReference(view)
        childrenViewRefs = SparseArrayCompat()
    }

    fun detachView() {
        rootViewRef = null
        childrenViewRefs?.clear()
        childrenViewRefs = null
    }

    private fun <V : View> getNullable(id: Int): V? {
        if (rootViewRef == null || childrenViewRefs == null) {
            return null
        }
        val rootView = rootViewRef!!.get() ?: return null
        val childViewRef = childrenViewRefs!!.get(id)
        var childView: View? = childViewRef?.get()
        if (childView == null) {
            childView = rootView.findViewById(id)
            if (childView != null) {
                childrenViewRefs!!.put(id, WeakReference(childView))
            }
        }
        return childView as V?
    }

    operator fun <V : View> get(id: Int) = getNullable<V>(id)
            ?: throw IllegalArgumentException("View with id $id not found")

    fun has(id: Int) = getNullable<View>(id) != null
}
