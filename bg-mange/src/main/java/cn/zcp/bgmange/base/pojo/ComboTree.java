package cn.zcp.bgmange.base.pojo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jquery easy ui 树形下拉实体类
 * @author pin
 *
 */
public class ComboTree {
	/**
	 * id：节点ID，对加载远程数据很重要。
	 text：显示节点文本。
	 state：节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
	 checked：表示该节点是否被选中。
	 iconCls：图标样式。
	 attributes: 被添加到节点的自定义属性。
	 children: 一个节点数组声明了若干节点。
	 */
	private String id;
	private String text;
	private String state;
	private String checked;
	private String iconCls;
	private Map<String, Object> attributes = new HashMap<>(); // 添加到节点的自定义属性
	private List<ComboTree> children;

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setChildren(List<ComboTree> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getState() {
		return state;
	}

	public String getChecked() {
		return checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public List<ComboTree> getChildren() {
		return children;
	}

	public Object removeAttribute(String key){
		return attributes.remove(key);
	}

	public Object putAttribute(String key, Object value){
		return attributes.put(key, value);
	}

	@Override
	public String toString() {
		return "ComboTree{" +
				"id='" + id + '\'' +
				", text='" + text + '\'' +
				", state='" + state + '\'' +
				", checked='" + checked + '\'' +
				", iconCls='" + iconCls + '\'' +
				", attributes=" + attributes +
				", children=" + children +
				'}';
	}
}
