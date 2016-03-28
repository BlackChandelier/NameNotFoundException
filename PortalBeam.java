package projlab;

public class PortalBeam implements Visitor {

	@Override
	public void visit(Visitable visitable) {
		Tabulator.tabMethod();
		System.out.println("-> PortalBeam.visit(nextTile)");
		Tabulator.tabMethod();
		System.out.println("<- void");		
	}

}
