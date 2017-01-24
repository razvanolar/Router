package code.router.utils.types;

/**
 * Used in LazyTreeItem to indicate if a node have a special meaning or not.
 *
 * Created by razvanolar on 24.01.2017.
 */
public enum TreeItemType {
  NORMAL(1), FOLDER(1), ROUTE(1);

  int type;
  TreeItemType(int type) {
    this.type = type;
  }

  public boolean isSystemType() {
    return type == 0;
  }
}
