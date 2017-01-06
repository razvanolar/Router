package code.router.utils.types;

/**
 * Created by razvanolar on 29.12.2016
 */
public enum ComponentTypes {
  MENU_BAR,
  TOOL_BAR,
  LEFT_TOOL_BAR,
  RIGHT_TOOL_BAR,
  ROUTES_TREE,
  PROJECTS_TREE,
  MAP,
  FOOTER,
  MAP_CONTEXT_MENU;

  public static ComponentTypes[] mainTypes() {
    return new ComponentTypes[]{
            MENU_BAR,
            TOOL_BAR,
            LEFT_TOOL_BAR,
            RIGHT_TOOL_BAR,
            ROUTES_TREE,
            PROJECTS_TREE,
            FOOTER};
  }
}
